// Gubaidullin Marat, Innopolis University, 13.02.2022
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 2.2.1
        /*
        int N = sc.nextInt(), K = sc.nextInt();         sc.nextLine();

        DoublyCircularBoundedQueue<String> myQueue = new DoublyCircularBoundedQueue<>(K);
        for (int i = 0; i < N; i++)
            myQueue.offer(sc.nextLine());

        for (int i = 0; i < K; i++)
            System.out.println(myQueue.poll());
         */

        // 2.2.2
        /*
        int N = sc.nextInt();       sc.nextLine();
        Set<String> FileSet = new Set<>(N);

        for (int i = 0; i < N; i++) {
            String CurrentLine = sc.nextLine();
            String[] ArrLine = CurrentLine.split(" ");

            try {
                if (ArrLine.length > 2 || ArrLine.length < 1 ||
                        (ArrLine.length == 1 && !ArrLine[0].equals("LIST")))
                    throw new IndexOutOfBoundsException();

                else if (ArrLine.length == 1)
                    FileSet.printSet();

                else if (ArrLine[0].equals("NEW")) {
                    if (ArrLine[1].charAt(ArrLine[1].length() - 1) == '/') {  // the last character
                        String[] CurrentLineStr = ArrLine[1].split("/");

                        if (FileSet.contains(CurrentLineStr[0]) || FileSet.contains(ArrLine[1]))  // if Set contains such directory or file
                            throw new IndexOutOfBoundsException();
                        FileSet.add(ArrLine[1]);
                    }

                    else {
                        if (FileSet.contains(ArrLine[1] + "/") || FileSet.contains(ArrLine[1]))  // if Set contains such directory or file
                            throw new IndexOutOfBoundsException();
                        FileSet.add(ArrLine[1]);
                    }
                }

                else if (ArrLine[0].equals("REMOVE")) {
                    if (!FileSet.contains(ArrLine[1])) // if there's no such file in set
                        throw new IndexOutOfBoundsException();
                    FileSet.remove(ArrLine[1]);
                }

                else
                    throw new IndexOutOfBoundsException();
            }
            catch (Exception e) {
                System.out.println("ERROR: cannot execute " + CurrentLine);
            }
        */

        // 2.2.3
        /*
        int N = sc.nextInt(), K = sc.nextInt();       sc.nextLine();

        BoundedStack<Set<String>> StatesHistory = new BoundedStack<>(K);
        Set<String> FileSet = new Set<>(N);
        StatesHistory.push(FileSet.copy()); // "null" position

        for (int i = 0; i < N; i++) {
            String CurrentLine = sc.nextLine();
            String[] ArrLine = CurrentLine.split(" ");

            try {
                if (ArrLine.length > 2 || ArrLine.length < 1 ||
                        (ArrLine.length == 1 && (!ArrLine[0].equals("LIST") && !ArrLine[0].equals("UNDO"))))
                    throw new IndexOutOfBoundsException();


                else if (ArrLine.length == 1) {
                    if (ArrLine[0].equals("LIST"))
                        FileSet.printSet();

                    else { // ArrLine[0].equals("UNDO")
                        if (StatesHistory.currentsize <= 1)
                            throw new IndexOutOfBoundsException();
                        StatesHistory.pop();
                        FileSet = StatesHistory.top().copy();

                    }

                } else if (ArrLine[0].equals("UNDO")) {
                    try {
                        if (Integer.parseInt(ArrLine[1]) >= StatesHistory.currentsize)
                            throw new IndexOutOfBoundsException();

                        for (int j = 0; j < Integer.parseInt(ArrLine[1]); j++)
                            StatesHistory.pop();

                        FileSet = StatesHistory.top().copy();
                    } catch (Exception e) {
                        throw new IndexOutOfBoundsException();
                    }
                } else if (ArrLine[0].equals("NEW")) {
                    if (ArrLine[1].charAt(ArrLine[1].length() - 1) == '/') {  // if the last character equals '/' => it is directory
                        String[] CurrentLineStr = ArrLine[1].split("/");
                        if (FileSet.contains(CurrentLineStr[0]) || FileSet.contains(ArrLine[1]))  // if Set contains such directory or file
                            throw new IndexOutOfBoundsException();
                    } else if (FileSet.contains(ArrLine[1] + "/") || FileSet.contains(ArrLine[1]))  // if Set contains such directory or file
                        throw new IndexOutOfBoundsException();

                    FileSet.add(ArrLine[1]);
                    StatesHistory.push(FileSet.copy());
                } else if (ArrLine[0].equals("REMOVE")) {
                    if (!FileSet.contains(ArrLine[1])) // if there's no such file in set
                        throw new IndexOutOfBoundsException();

                    FileSet.remove(ArrLine[1]);
                    StatesHistory.push(FileSet.copy());
                } else throw new IndexOutOfBoundsException();
            } catch (Exception e) {
                System.out.println("ERROR: cannot execute " + CurrentLine);
            }
        }

         */


    }

}
