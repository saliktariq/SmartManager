package app.smartmanager.helper

class SMTPAccount {
    companion object{
        /*
        Following keys are generated on SendGrid to use their smtp server in order to send messages
        SendGrid provide a generous free tier allowance of 100 emails per day,
        if required, client will be switched on paid plan.
         */
        const val USER: String = "######"
        const val PASSWORD: String = "####################################"
    }
}
