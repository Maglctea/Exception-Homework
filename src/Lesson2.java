// ....код....
// Ниже испраленный фрагмент кода
try {
    int d = 0;
    double catchedRes1 = intArray[8] / d;
    System.out.println("catchedRes1 = " + catchedRes1);
} catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
    System.out.println(e.getMessage());
}
// ....код....

