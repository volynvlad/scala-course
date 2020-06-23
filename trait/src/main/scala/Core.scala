object Core {
    class OrdinaryCar(override val model: String)
        extends Base.Car {
            override def topSpeedInKmPerHour: Int = 220
            override def topAccelerationInRmp: Int = 8000
        }
    
    class SportsCar(override val model: String)
        extends Base.Car {
            override def topSpeedInKmPerHour: Int = 300
            override def topAccelerationInRmp: Int = 11000
        }
}