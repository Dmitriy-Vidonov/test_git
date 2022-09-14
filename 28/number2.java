//задачка 2 - одометр
public class number2 {
  public static void main(String[] args) {
    int[] arr = {10, 1, 20, 2, 20, 3, 15, 4, 20, 5}; //85 км
    int result = arr[0] * arr[1];
    for(int i=2; i<=arr.length-2; i+=2) {
        result += arr[i] * (arr[i+1] - arr[i-1]);
    }
    System.out.println("res = " + result);
  }
} 