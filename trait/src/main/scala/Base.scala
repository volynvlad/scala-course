object Base {
    abstract class Car {
        def model: String
        def topSpeedInKmPerHour: Int
        def topAccelerationInRmp: Int

        override def toString(): String = {
            val brand = getClass.getSimpleName

            brand + " " + model + " " + topSpeedInKmPerHour + topAccelerationInRmp
        }
    }
}