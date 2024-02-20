package Exam_Advance_2;

import java.util.Scanner;
import java.util.Stack;

public class ISBNValidator {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Bước 1: Lấy số ISBN gồm 10 chữ số từ người dùng
            System.out.println("Nhập số ISBN (10 chữ số): ");
            String isbn = scanner.nextLine();

            // Bước 2: Kiểm tra xem người dùng có nhập số có mười chữ số hay không
            if (isbn.length() != 10) {
                System.out.println("Số ISBN phải có đúng 10 chữ số.");
                return;
            }

            // Bước 3: Sử dụng Stack để lưu trữ các chữ số và thực hiện tính tổng theo công thức
            Stack<Integer> digitStack = new Stack<>();
            for (int i = 0; i < 10; i++) {
                char digitChar = isbn.charAt(i);
                if (!Character.isDigit(digitChar)) {
                    System.out.println("Số ISBN chỉ được chứa các chữ số.");
                    return;
                }
                int digit = Character.getNumericValue(digitChar);
                digitStack.push(digit);
            }

            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i * digitStack.pop();
            }

            // Bước 4: Kiểm tra điều kiện và đưa ra kết luận
            if (sum % 11 == 0) {
                System.out.println("Số ISBN nhập vào là hợp lệ.");
            } else {
                System.out.println("Số ISBN nhập vào không hợp lệ.");
            }
        }
    }
