class Set<T> implements ISet<T>{
    public int SET_MAX_SIZE;
    public int current_size;
    int prime_size;
    private Object[] Arr;

    public Set(int SMS){
        try {
            if (SMS <= 0)
                throw new IndexOutOfBoundsException();
            SET_MAX_SIZE = SMS;
            Arr = new Object[SMS];
            current_size = 0;
            prime_size = 0;
        }
        catch (Exception e) {
            System.out.println("Exception: The size of a Set cannot be less or equal zero!");
        }
    }


    @Override
    public void add(Object item) { // O(n) in worst case; O(1) average and best case
        try {
            boolean itcontains = contains(item);
            if ((current_size == SET_MAX_SIZE) && !itcontains)
                throw new IndexOutOfBoundsException();

            if ((current_size == SET_MAX_SIZE) && itcontains)
                return;
            int hashvalue1 = hashCode1(item.hashCode());
            int hashvalue2 = hashCode2(item.hashCode());

            while (Arr[hashvalue1] != null) {
                if (Arr[hashvalue1].equals(""))
                    break;

                hashvalue1 += hashvalue2;
                hashvalue1 %= SET_MAX_SIZE;
            }

            Arr[hashvalue1] = item;
            this.current_size++;
        }
        catch (Exception e) {
            System.out.println("The set is full!");
        }
    }

    @Override
    public void remove(Object item) { // O(n) - worst case; O(1) best and average case
        int hashvalue1 = hashCode1(item.hashCode());
        int hashvalue2 = hashCode2(item.hashCode());

        while (Arr[hashvalue1] != null) {
            if (item.equals(Arr[hashvalue1])) {
                Arr[hashvalue1] = "";
                current_size--;
                return;
            }

            hashvalue1 += hashvalue2;
            hashvalue1 %= SET_MAX_SIZE;
        }
    }

    @Override
    public boolean contains(Object item) { // O(n) - worst case; O(1) - best case and average case,
        int hashvalue1 = hashCode1(item.hashCode());
        int hashvalue2 = hashCode2(item.hashCode());

        while (!item.equals(Arr[hashvalue1]) && Arr[hashvalue1] != null) {
            hashvalue1 += hashvalue2;
            hashvalue1 %= SET_MAX_SIZE;
        }

        return item.equals(Arr[hashvalue1]);
    }

    @Override
    public int size() { // O(1)
        return current_size;
    }

    @Override
    public boolean isEmpty() { // O(1)
        return current_size == 0;
    }

    public Set<T> copy() { // O(n)
        Set<T> Set1 = new Set<>(SET_MAX_SIZE);

        for (int j = 0; j < SET_MAX_SIZE; j++)
            if (Arr[j] != null)
                Set1.add(Arr[j]);

        return Set1;
    }

    public int hashCode1(int key) { // O(1)
        return Math.abs(key) % SET_MAX_SIZE;
    }

    public int hashCode2(int key) { // O(1)
        return getPrime() - Math.abs(key) % getPrime();
    }

    public int getPrime() { // O(n^2) - worst case; O(n) best and average case
        if (prime_size != 0)
            return prime_size;

        for (int i = SET_MAX_SIZE; i >= 1; i--) {
            int k = 0;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    k++;
                    break;
                }
            }
            if (k == 0) {
                prime_size = i;
                return i;
            }
        }

        prime_size = 3;
        return 3;
    }

    public void printSet() {// O(n) time complexity
        for (int i = 0; i < SET_MAX_SIZE; i++)
            if (Arr[i] != null && Arr[i] != "")
                System.out.print(Arr[i] + " ");

        System.out.println();
    }
}