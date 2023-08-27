package com.shpp.p2p.cs.ovoskresenskyy.assignment11.model;

/**
 * This class is representing of the pair of the operands
 * placed on the side of the operator.
 */
public class OperandPair {

    private Token leftValue;
    private Token rightValue;

    /**
     * Value on the left side of the operator
     */
    private double left;
    /**
     * Value on the right side of the operator
     */
    private double right;
    /**
     * A marker is current pair is already calculated
     */
    private boolean isCalculated;

    public OperandPair(double left, double right) {
        this.left = left;
        this.right = right;
    }

    /**
     * @return Left value of the pair
     */
    public double getLeft() {
        return left;
    }

    /**
     * @return Right value of the pair
     */
    public double getRight() {
        return right;
    }

    /**
     * @return Is current pair already calculated
     */
    public boolean isCalculated() {
        return isCalculated;
    }

    /**
     * @param left Value to set the left part of the pair.
     *             Often can be a result of calculation neighbor pair.
     */
    public void setLeft(double left) {
        this.left = left;
    }

    /**
     * @param right Value to set the right part of the pair.
     *              Often can be a result of calculation neighbor pair.
     */
    public void setRight(double right) {
        this.right = right;
    }

    /**
     * @param calculated Value if current pair is already calculated
     *                   and no need to take into account in calculations anymore.
     */
    public void setCalculated(boolean calculated) {
        isCalculated = calculated;
    }
}
