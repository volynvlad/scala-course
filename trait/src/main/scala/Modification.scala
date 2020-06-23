object Modification {
    trait Spoiler extends Base.Car {
        abstract override def topSpeedInKmPerHour: Int = (super.topSpeedInKmPerHour * 1.1).toInt
    }

    trait EngineControlUnit extends Core.OrdinaryCar {
        override def topSpeedInKmPerHour: Int = (super.topSpeedInKmPerHour * 1.5).toInt 
    }

    trait turboCharger extends Core.OrdinaryCar {
        override def topAccelerationInRmp: Int = 
            (super.topAccelerationInRmp * 1.25).toInt
    }
}