package org.firstinspires.ftc.teamcode.utilities

/*
 * Written by Doug Lea and Martin Buchholz with assistance from
 * members of JCP JSR-166 Expert Group and released to the public
 * domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

/*
 * Source:
 * http://gee.cs.oswego.edu/cgi-bin/viewcvs.cgi/jsr166/src/jsr166e/extra/AtomicDouble.java?revision=1.13
 * (Modified to adapt to guava coding conventions and
 * to use AtomicLongFieldUpdater instead of sun.misc.Unsafe)
 */



import java.lang.Double.doubleToRawLongBits
import java.lang.Double.longBitsToDouble

import java.util.concurrent.atomic.AtomicLongFieldUpdater

/**
 * A `double` value that may be updated atomically. See the [ ] package specification for description of the properties of atomic
 * variables. An `AtomicDouble` is used in applications such as atomic accumulation, and
 * cannot be used as a replacement for a [Double]. However, this class does extend `Number` to allow uniform access by tools and utilities that deal with numerically-based classes.
 *
 *
 * <a id="bitEquals"></a>This class compares primitive `double` values in methods such as
 * [.compareAndSet] by comparing their bitwise representation using [ ][Double.doubleToRawLongBits], which differs from both the primitive double `==` operator and
 * from [Double.equals], as if implemented by:
 *
 * <pre>`static boolean bitEquals(double x, double y) {
 * long xBits = Double.doubleToRawLongBits(x);
 * long yBits = Double.doubleToRawLongBits(y);
 * return xBits == yBits;
 * }
`</pre> *
 *
 *
 * It is possible to write a more scalable updater, at the cost of giving up strict atomicity.
 * See for example [
 * DoubleAdder](http://gee.cs.oswego.edu/dl/jsr166/dist/jsr166edocs/jsr166e/DoubleAdder.html) and [
 * DoubleMaxUpdater](http://gee.cs.oswego.edu/dl/jsr166/dist/jsr166edocs/jsr166e/DoubleMaxUpdater.html).
 *
 * @author Doug Lea
 * @author Martin Buchholz
 * @since 11.0
 */
class AtomicDouble : Number, java.io.Serializable {


    @Volatile
    @Transient
    private var value: Long = 0

    /**
     * Creates a new `AtomicDouble` with the given initial value.
     *
     * @param initialValue the initial value
     */
    constructor(initialValue: Double) {
        value = doubleToRawLongBits(initialValue)
    }

    /** Creates a new `AtomicDouble` with initial value `0.0`.  */
    constructor() {
        // assert doubleToRawLongBits(0.0) == 0L;
    }

    /**
     * Gets the current value.
     *
     * @return the current value
     */
    fun get(): Double = longBitsToDouble(value)

    /**
     * Sets to the given value.
     *
     * @param newValue the new value
     */
    fun set(newValue: Double) {
        val next = doubleToRawLongBits(newValue)
        value = next
    }

    /**
     * Eventually sets to the given value.
     *
     * @param newValue the new value
     */
    fun lazySet(newValue: Double) {
        val next = doubleToRawLongBits(newValue)
        updater.lazySet(this, next)
    }

    /**
     * Atomically sets to the given value and returns the old value.
     *
     * @param newValue the new value
     * @return the previous value
     */
    fun getAndSet(newValue: Double): Double {
        val next = doubleToRawLongBits(newValue)
        return longBitsToDouble(updater.getAndSet(this, next))
    }

    /**
     * Atomically sets the value to the given updated value if the current value is [bitwise equal](#bitEquals) to the expected value.
     *
     * @param expect the expected value
     * @param update the new value
     * @return `true` if successful. False return indicates that the actual value was not
     * bitwise equal to the expected value.
     */
    fun compareAndSet(expect: Double, update: Double): Boolean {
        return updater.compareAndSet(this, doubleToRawLongBits(expect), doubleToRawLongBits(update))
    }

    /**
     * Atomically sets the value to the given updated value if the current value is [bitwise equal](#bitEquals) to the expected value.
     *
     *
     * May [
 * fail spuriously](http://download.oracle.com/javase/7/docs/api/java/util/concurrent/atomic/package-summary.html#Spurious) and does not provide ordering guarantees, so is only rarely an appropriate
     * alternative to `compareAndSet`.
     *
     * @param expect the expected value
     * @param update the new value
     * @return `true` if successful
     */
    fun weakCompareAndSet(expect: Double, update: Double): Boolean {
        return updater.weakCompareAndSet(
                this, doubleToRawLongBits(expect), doubleToRawLongBits(update))
    }

    /**
     * Atomically adds the given value to the current value.
     *
     * @param delta the value to add
     * @return the previous value
     */
    fun getAndAdd(delta: Double): Double {
        while (true) {
            val current = value
            val currentVal = longBitsToDouble(current)
            val nextVal = currentVal + delta
            val next = doubleToRawLongBits(nextVal)
            if (updater.compareAndSet(this, current, next)) {
                return currentVal
            }
        }
    }

    fun add(delta: Double) {
        addAndGet(delta)
    }

    /**
     * Atomically adds the given value to the current value.
     *
     * @param delta the value to add
     * @return the updated value
     */
    fun addAndGet(delta: Double): Double {
        while (true) {
            val current = value
            val currentVal = longBitsToDouble(current)
            val nextVal = currentVal + delta
            val next = doubleToRawLongBits(nextVal)
            if (updater.compareAndSet(this, current, next)) {
                return nextVal
            }
        }
    }

    /**
     * Returns the String representation of the current value.
     *
     * @return the String representation of the current value
     */
    override fun toString(): String {
        return get().toString()
    }

    /**
     * Returns the value of this `AtomicDouble` as an `int` after a narrowing primitive
     * conversion.
     */
    override fun toInt(): Int = get().toInt()

    /**
     * Returns the value of this `AtomicDouble` as a `long` after a narrowing primitive
     * conversion.
     */
    override fun toLong() = get().toLong()

    /**
     * Returns the value of this `AtomicDouble` as a `float` after a narrowing primitive
     * conversion.
     */
    override fun toFloat() = get().toFloat()

    /** Returns the value of this `AtomicDouble` as a `double`.  */
    override fun toDouble(): Double = get()

    override fun toByte() = get().toByte()
    override fun toChar() = get().toChar()
    override fun toShort() = get().toShort()


    /**
     * Saves the state to a stream (that is, serializes it).
     *
     * @serialData The current value is emitted (a `double`).
     */
    @Throws(java.io.IOException::class)
    private fun writeObject(s: java.io.ObjectOutputStream) {
        s.defaultWriteObject()

        s.writeDouble(get())
    }

    /** Reconstitutes the instance from a stream (that is, deserializes it).  */
    @Throws(java.io.IOException::class, ClassNotFoundException::class)
    private fun readObject(s: java.io.ObjectInputStream) {
        s.defaultReadObject()

        set(s.readDouble())
    }

    companion object {
        private const val serialVersionUID = 0L

        private val updater = AtomicLongFieldUpdater.newUpdater(AtomicDouble::class.java, "value")
    }
}