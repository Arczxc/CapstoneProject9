package com.example.capstoneproject9.presentation.main.components.drawer.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.capstoneproject9.R
import com.example.capstoneproject9.components.cards.FAQCARD1
import com.example.capstoneproject9.presentation.main.components.FAQ


@Composable
@ExperimentalMaterial3Api
fun ItemFAQ(
    navigateToMyTicketScreen: () -> Unit,
    navigateToSubmitTicketScreen: () -> Unit,
){
    FAQ{ faqs ->

        val showPrivacyPolicy = remember{ mutableStateOf(true) }

        Column {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(faqs) { faq ->
                    FAQCARD1(
                        faq = faq
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = { navigateToMyTicketScreen() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.accent)
                    )
                ) {
                    Text(text = "MY TICKET")
                }

                Button(
                    onClick = { navigateToSubmitTicketScreen() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.accent)
                    )
                ) {
                    Text(text = "SUBMIT TICKET")
                }
                /*TextButton(
                    onClick = { showPrivacyPolicy.value = true },
                    colors = ButtonDefaults.buttonColors(Color.Green)
                ) {
                    Text(text = "Privacy Policy")
                }*/
            }


            if (showPrivacyPolicy.value) {
                AlertDialog(
                    onDismissRequest = {
                        showPrivacyPolicy.value = false
                    },
                    containerColor = Color.Gray,
                    text = {
                        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                            Text(
                                text =
                                "Privacy Policy for Jah Jahs Ordering System\n" +
                                        "\n" +
                                        "Effective Date: [07/20/23]\n" +
                                        "\n" +
                                        "1. Introduction\n" +
                                        "\n" +
                                        "Welcome to Jah Jahs Ordering System (\"we,\" \"us,\" or \"our\"). This Privacy Policy outlines how we collect, use, and protect your information when you use our mobile application exclusively for legal transactions.\n" +
                                        "\n" +
                                        "2. Information We Collect\n" +
                                        "\n" +
                                        "\t2.1. Information You Provide\n" +
                                        "\n" +
                                        "\n" +
                                        "Account Information: To use Jah Jahs Ordering System, you may need to log-in using google accoount. We collect your name, email address, and phone number to set up and manage your account.\n" +
                                        "\n" +
                                        "Transaction Details: When you engage in legal transactions through our app, we collect necessary transaction information, including order details and payment information. This information is essential to facilitate your legal transactions securely.\n" +
                                        "\n" +
                                        "\n" +
                                        "3. How We Use Your Information\n" +
                                        "\n" +
                                        "We use the information we collect for the following purposes:\n" +
                                        "\n" +
                                        "\tTo provide and manage legal transaction services.\n" +
                                        "\tTo process and complete legal transactions and payments.\n" +
                                        "\tTo communicate with you regarding your account, orders, and updates.\n" +
                                        "\tTo enhance our app's functionality and user experience.\n" +
                                        "\tTo respond to your inquiries and provide support.\n" +
                                        "\tTo comply with legal requirements and protect our rights.\n" +
                                        "4. Data Sharing and Disclosure\n" +
                                        "\n" +
                                        "We may share your information in the following circumstances:\n" +
                                        "\n" +
                                        "Service Providers: We engage third-party service providers to assist with app development, payment processing, hosting, and customer support. They access your information as necessary to perform their services.\n" +
                                        "\n" +
                                        "Legal Compliance: We may disclose your information when required by law or to protect our rights, safety, or property.\n" +
                                        "\n" +
                                        "\n" +
                                        "5. Security\n" +
                                        "\n" +
                                        "We take reasonable measures to safeguard your information against unauthorized access, disclosure, alteration, or destruction. However, no method of transmission over the internet or electronic storage is entirely secure.\n" +
                                        "\n" +
                                        "\n" +
                                        "6. Your Choices\n" +
                                        "\n" +
                                        "Account Information: You can review and update your account information by accessing your account settings in the app.\n" +
                                        "\n" +
                                        "\n" +
                                        "7. Children's Privacy\n" +
                                        "\n" +
                                        "Jah Jahs Ordering System is not intended for individuals under the age of 13, and we do not knowingly collect personal information from them.\n" +
                                        "\n" +
                                        "\n" +
                                        "8. Changes to this Privacy Policy\n" +
                                        "\n" +
                                        "We may update this Privacy Policy to reflect changes in our practices or for legal or operational reasons. We will notify you of any significant changes within the app.\n" +
                                        "\n" +
                                        "\n" +
                                        "9. Contact Us\n" +
                                        "\n" +
                                        "If you have any questions, concerns, or requests related to this Privacy Policy or your personal information, please contact us at ."
                            )

                        }

                    },
                    title = {
                        Text(text = "Privacy Policy")
                    },
                    confirmButton = {
                        Text(
                            text = "Ok",
                            color = Color.Black,
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable(
                                    onClick = {
                                        showPrivacyPolicy.value = false
                                    }
                                )
                        )
                    },
                    dismissButton = {
                        /*Text(
                            text = "cancel",
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable(
                                    onClick = {
                                        showTooltip.value = false
                                    }
                                )
                        )*/
                    },
                    textContentColor = Color.White,
                    shape = RectangleShape
                )
            }


        }
    }
}