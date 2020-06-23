sealed trait CreditCard {
    import CreditCard._
    def number: String

    final def isValid: Boolean = 
        isInstanceOf[Valid]

    final def isNotValid: Boolean = 
        !isValid
}

object CreditCard {
    object Invalid {
        private[CreditCard] def apply(number: String): Invalid =
            new Invalid(number)
    }
    final case class Invalid private (number: String) extends CreditCard
    final case class   Valid private (number: String) extends CreditCard

    def apply(number: String): CreditCard = 
        if (isValid(number))
            Valid(number)
        else
            Invalid(number)
    
    private def isValid(number: String): Boolean = false
}