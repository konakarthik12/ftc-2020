package org.firstinspires.ftc.teamcode.utilities

open class Unit(val name: String, val ratio: Double) {
    fun convertToBaseUnit(amount: Double) = amount * ratio
    fun convertFromBaseUnit(amount: Double) = amount / ratio
}

open class Quantity<T : Unit>(val amount: Double, val unit: T) {
    fun to(unit: T): Quantity<T> {
        val baseUnit = this.unit.convertToBaseUnit(amount)
        return Quantity(unit.convertFromBaseUnit(baseUnit), unit)
    }

    operator fun plus(quantity: Quantity<T>): Quantity<T> {
        val converted = quantity.to(this.unit).amount
        val amount = this.amount + converted
        return Quantity(amount, this.unit)
    }

    operator fun minus(quantity: Quantity<T>): Quantity<T> {
        val converted = quantity.to(this.unit).amount
        val amount = this.amount - converted
        return Quantity(amount, this.unit)
    }

    operator fun times(quantity: Quantity<T>): Quantity<T> {
        val converted = quantity.to(this.unit).amount
        val amount = this.amount * converted
        return Quantity(amount, this.unit)
    }

    operator fun div(quantity: Quantity<T>): Quantity<T> {
        val converted = quantity.to(this.unit).amount
        val amount = this.amount / converted
        return Quantity(amount, this.unit)
    }
}

class Distance(name: String, ratio: Double) : Unit(name, ratio) {
    companion object Factory {
        val Mile = Distance("Mile", 1.60934 * 1000.0)
        val Feet = Distance("Feet", 0.3048)
        val Kilometer = Distance("Kilometer", 1000.0)
        val Meter = Distance("Meter", 1.0)
        val Centimeter = Distance("Centimeter", 0.01)
        val Millimeter = Distance("Millimeter", 0.001)
    }
}

val Quantity<Distance>.miles get() = this.to(Distance.Mile)
val Quantity<Distance>.Feet get() = this.to(Distance.Feet)
val Quantity<Distance>.kilometers get() = this.to(Distance.Kilometer)
val Quantity<Distance>.meters get() = this.to(Distance.Meter)
val Quantity<Distance>.centimeters get() = this.to(Distance.Centimeter)
val Quantity<Distance>.millimeters get() = this.to(Distance.Millimeter)

val Number.meters: Quantity<Distance> get() = Quantity(this.toDouble(), Distance.Meter)
val Number.feet: Quantity<Distance> get() = Quantity(this.toDouble(), Distance.Feet)
val Number.kilometers: Quantity<Distance> get() = Quantity(this.toDouble(), Distance.Kilometer)
val Number.miles: Quantity<Distance> get() = Quantity(this.toDouble(), Distance.Mile)
val Number.centimeter: Quantity<Distance> get() = Quantity(this.toDouble(), Distance.Centimeter)
val Number.millimeter: Quantity<Distance> get() = Quantity(this.toDouble(), Distance.Millimeter)
