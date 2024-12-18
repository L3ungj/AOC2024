package src.q17;

import java.util.ArrayList;

import src.utils.InputFileReader;
import src.utils.Utils;

public class Q17a {
    static String readdAfterColon(String s) {
        return s.split(": ")[1];
    }

    long ra, rb, rc;

    long combo(int i) {
        if (i <= 3)
            return i;
        if (i == 4)
            return ra;
        if (i == 5)
            return rb;
        if (i == 6)
            return rc;
        return -1;
    }

    void main_() {
        String[] lines = InputFileReader.readAllLines();
        ra = Long.parseLong(readdAfterColon(lines[0]));
        rb = Long.parseLong(readdAfterColon(lines[1]));
        rc = Long.parseLong(readdAfterColon(lines[2]));
        Integer[] program = Utils.splitMapInt(readdAfterColon(lines[4]), ",");

        int n = program.length;
        ArrayList<Long> outputs = new ArrayList<>();
        for (int i = 0; i < n; i += 2) {
            int opcode = program[i];
            int operand = program[i + 1];
            switch (opcode) {
                case 0:
                    ra >>= combo(operand);
                    break;
                case 1:
                    rb ^= operand;
                    break;
                case 2:
                    rb = combo(operand) % 8;
                    break;
                case 3:
                    if (ra != 0) {
                        i = operand - 2;
                    }
                    break;
                case 4:
                    rb ^= rc;
                    break;
                case 5:
                    outputs.add(combo(operand) % 8);
                    break;
                case 6:
                    rb = ra >> combo(operand);
                    break;
                case 7:
                    rc = ra >> combo(operand);
                    break;
                default:
                    break;
            }
        }
        System.out.println(String.join(",", outputs.stream().map(String::valueOf).toArray(String[]::new)));
    }

    public static void main(String[] args) {
        new Q17a().main_();
    }
}
