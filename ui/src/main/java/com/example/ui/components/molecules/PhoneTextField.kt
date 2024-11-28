package com.example.ui.components.molecules

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.ui.R
import com.example.ui.components.atoms.CustomTextField
import com.example.ui.theme.BlackTertiary
import com.example.ui.theme.GraduationProjectTheme

@Composable
fun PhoneTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    CustomTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = stringResource(R.string.phone_number),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.image_phone),
                contentDescription = stringResource(R.string.phone_number),
                tint = BlackTertiary
            )
        },
    )
}


//@Preview(showSystemUi = true)
@Composable
fun PreviewPhoneTextField() {
    GraduationProjectTheme {
        var textState by remember { mutableStateOf("") }

        PhoneTextField(textState, { textState = it })
    }
}