package com.example.capstoneproject9.presentation.edit_profile_info.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.presentation.edit_profile_info.EditProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileContent(
    viewModel: EditProfileViewModel = hiltViewModel()
){

    val recipientName = remember { mutableStateOf("") }
    var contactNumber by rememberSaveable   { mutableStateOf("") }
    val contactNumberLimit = 11
    var isError by rememberSaveable {
        mutableStateOf(false)
    }
    val address1 = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val country = remember { mutableStateOf("") }
    val zipCode = remember { mutableStateOf("") }

    fun validate(text: String){
        isError = text.length > contactNumberLimit
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ){
        Column(modifier = Modifier.fillMaxWidth()) {

            TextField(
                modifier = Modifier,
                value = recipientName.value,
                onValueChange = { recipientName.value = it },
                label = { Text(text = "Reciepient Name")},
                singleLine = true,
                leadingIcon = {
                    Image(imageVector = Icons.Default.Person , contentDescription = null)
                }
            )


            TextField(
                value = contactNumber,
                onValueChange = {
                    if (it.length <= contactNumberLimit) contactNumber = it
                    validate(contactNumber)
                                },
                label = { Text(text = "Phone Number")},
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                isError = isError,
                keyboardActions = KeyboardActions { validate(contactNumber)},
                modifier = Modifier.semantics {
                    if (isError) error("Number invalid")
                },
                supportingText = {
                    Text(
                        text = "${contactNumber.length} / $contactNumberLimit",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                leadingIcon = {
                    Image(imageVector = Icons.Default.Call , contentDescription = null)
                }
            )



            TextField(
                value = address1.value,
                onValueChange = { address1.value = it },
                label = { Text(text = "House Number, Street, Baranggay")},
                singleLine = true,
                leadingIcon = {
                    Image(imageVector = Icons.Default.LocationOn , contentDescription = null)
                }
            )


            TextField(
                value =city.value,
                onValueChange = { city.value = it },
                label = { Text(text = "City")},
                singleLine = true,
                leadingIcon = {
                    Image(imageVector = Icons.Default.LocationOn , contentDescription = null)
                }
            )


            TextField(
                value =country.value,
                onValueChange = { country.value = it },
                label = { Text(text = "Country")},
                singleLine = true,
                leadingIcon = {
                    Image(imageVector = Icons.Default.LocationOn , contentDescription = null)
                }
            )


            TextField(
                value =zipCode.value,
                onValueChange = { zipCode.value = it },
                label = { Text(text = "Zip")},
                singleLine = true,
                leadingIcon = {
                    Image(imageVector = Icons.Default.LocationOn , contentDescription = null)
                }
            )


            Button(onClick = {
                viewModel.saveProfile(recipientName.value, contactNumber, address1.value, city.value  , country.value, zipCode.value)
            }) {
                Text(text = "SAVE PROFILE")
            }
        }
    }

    SaveProfile()

}