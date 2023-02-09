package cinema;
import java.util.Scanner;

public class Cinema {


    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        final int rows=scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int seatsInRows = scanner.nextInt();
        int option;
        int currentIncome = 0;

        char seat = '1';
        char[][] seats = new char[rows+1][seatsInRows+1];
        seats[0][0]=' ';
        for (int i = 1;i<seatsInRows+1;i++){
            seats[0][i]= seat;
            seat++;
        }
        seat = '1';
        for (int i = 1;i<rows+1;i++){
            seats[i][0]= seat;
            seat++;
        }
        for (int i = 1;i<rows+1;i++){
            for(int j = 1; j<seatsInRows+1;j++){
                seats[i][j]='S';
            }
        }
        while(true){
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            option= scanner.nextInt();
            switch (option){
                case 1:
                    showSeats(seats, rows,seatsInRows);
                    System.out.println("");
                    break;
                case 2:
                    currentIncome+=buyTicket(seats,rows,seatsInRows);
                    break;
                case 3:
                    showStats(seats,rows,seatsInRows,currentIncome);
                    break;
                case 0:
                    return;


            }
        }


    }
    public static int ticketsPurchased(char[][] seats,int rows,int seatsInRows){
        int purchased = 0;
        for (int i = 1;i<rows+1;i++){
            for(int j = 1; j<seatsInRows+1;j++){
                if(seats[i][j]=='B'){
                    purchased+=1;
                }
            }
        }
        return purchased;
    }
    public static float percentageSeatsTaken(char[][] seats,int rows,int seatsInRows){
        float percentage = 0;
        float seatsTaken=0;
        int allSeats=rows*seatsInRows;
        for (int i = 1;i<rows+1;i++){
            for(int j = 1; j<seatsInRows+1;j++){
                if(seats[i][j]=='B'){
                    seatsTaken+=1;
                }
            }
        }
        percentage=(seatsTaken/allSeats)*100;
        return percentage;
    }
    public static void showSeats(char[][] seats, int rows,int seatsInRows){
        System.out.println("Cinema:");
        for (int i = 0;i<rows+1;i++){
            for(int j = 0; j<seatsInRows+1;j++){
                System.out.printf("%c ",seats[i][j]);
            }
            System.out.println("");
        }
    }
    public static int buyTicket(char[][] seats, int rows,int seatsInRows){
        Scanner scanner = new Scanner(System.in);
        int normalTicket = 10;
        int cheaperTicket = 8;
        int ticketCost;
        while(true){
            System.out.println("Enter a row number:");
            int rowBought = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatBought = scanner.nextInt();
            if(rowBought>rows || seatBought>seatsInRows){
                System.out.println("Wrong input!");
                continue;
            }
            else if (seats[rowBought][seatBought]=='B') {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            System.out.print("Ticket price: ");
            if(rows*seatsInRows<=60){
                System.out.printf("$%d\n",normalTicket);
                ticketCost=normalTicket;
            }
            else if(rows%2==0){
                if(rowBought<=(rows/2)){
                    System.out.printf("$%d\n",normalTicket);
                    ticketCost=normalTicket;
                }
                else {
                    System.out.printf("$%d\n",cheaperTicket);
                    ticketCost=cheaperTicket;
                }
            }
            else{
                if(rowBought<=(rows/2)){
                    System.out.printf("$%d\n",normalTicket);
                    ticketCost=normalTicket;
                }
                else {
                    System.out.printf("$%d\n",cheaperTicket);
                    ticketCost=cheaperTicket;
                }
            }
            seats[rowBought][seatBought]='B';
            return ticketCost;
        }}
    public static int totalIncome(int rows, int seatsInRows){
        int income = 0;
        int normalTicket = 10;
        int cheaperTicket = 8;
        if(rows*seatsInRows<=50){
            income = rows*seatsInRows*normalTicket;
        }
        else {
            if (rows%2==0){
                income=((rows*seatsInRows)/2)*normalTicket+((rows*seatsInRows)/2)*cheaperTicket;
            }
            else {
                income= ((rows*seatsInRows-rows)/2*normalTicket)+(rows*seatsInRows+rows)/2*cheaperTicket;
            }
        }
        return income;
    }
    public static void showStats(char[][] seats,int rows,int seatsInRows,int currentIncome){
        System.out.printf("Number of purchased tickets: %d\n", ticketsPurchased(seats,rows,seatsInRows));
        System.out.printf("Percentage: %.2f%%\n", percentageSeatsTaken(seats,rows,seatsInRows));
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome(rows,seatsInRows));
    }
}