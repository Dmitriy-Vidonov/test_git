public class ex7_google_compet{
  public static void main(String[] args) {
    //1) перевести строку в массив символов
    String str = "одно слово дваа словаа трисловиссимо";
    char[] massivBukv = str.toCharArray(); //массив символов на основе строки
    int len = 10;
    
    for(int i=0; i<massivBukv.length; i++){
      System.out.print(massivBukv[i] + "");
    }
    System.out.println();
    System.out.println("размер строки = " + massivBukv.length); //36
    
    //2) посчитать размерность матрицы для размещения строк
    int rows = massivBukv.length/(len/2 + 1) + 1;
    System.out.println("rows = " + rows);

    char[][] mainArray = new char[rows][len];

    int count = 0;
    for(int i=0; i<rows; i++){ //проход по строкам
      for(int j=0; j<len; j++) { //проход по столбцам
            if(count<massivBukv.length) {
              mainArray[i][j] = massivBukv[count]; 
              count++;    
            }
      }
    }
    for(int i=0; i<rows; i++){
      for(int j=0; j<len; j++){
        System.out.print(mainArray[i][j]);
      }
      System.out.println();
    }
    
  }
}