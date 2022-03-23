package app.smartmanager.helper

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
/*
The following class is implemented after reading the code at https://gist.github.com/BlackthornYugen/1b3e1ff4426294e7054c9a7190e8f2cd
The following class is an adaptation of the mentioned github class
 */
class Mailer {
    companion object {
        fun sendMail(recipient: String, mailSubject: String, body: String) {
            val userName = SMTPAccount.USER
            val password = SMTPAccount.PASSWORD

            val emailFrom = "info@smartmanager.app"
            val emailTo = recipient.toString()
            val emailCC = "info@smartmanager.app"

            val subject = mailSubject.toString()
            val text = body.toString()

            val props = Properties()
            injectProperties(props, "mail.smtp.host", "smtp.sendgrid.net")
            injectProperties(props, "mail.smtp.port", "587")
            injectProperties(props, "mail.smtp.auth", "true")
            injectProperties(props, "mail.smtp.starttls.enable", "true")

            val session = Session.getDefaultInstance(props, object : javax.mail.Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(userName, password)
                }
            })

            session.debug = true

            try {
                val mimeMessage = MimeMessage(session)
                mimeMessage.setFrom(InternetAddress(emailFrom))
                mimeMessage.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailTo, false)
                )
                mimeMessage.setRecipients(
                    Message.RecipientType.CC,
                    InternetAddress.parse(emailCC, false)
                )
                mimeMessage.setText(text)
                mimeMessage.subject = subject
                mimeMessage.sentDate = Date()

                val smtpTransport = session.getTransport("smtp")
                GlobalScope.launch {
                    smtpTransport.connect()
                    smtpTransport.sendMessage(mimeMessage, mimeMessage.allRecipients)
                    smtpTransport.close()
                }

            } catch (messagingException: MessagingException) {
                messagingException.printStackTrace()
            }


        }
        private fun injectProperties(props: Properties, key: String, value: String) {
            if (!props.containsKey(key)) {
                props[key] = value
            }
        }

    }



}