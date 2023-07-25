package com.example.capstoneproject9.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Hyperlink(
    fullText: String,
    linkText: String
){
    val annotatedString = buildAnnotatedString {
        append(fullText)

        val startIndex = fullText.indexOf("PAY HERE")
        val endIndex = startIndex + "PAY HERE".length

        addStyle(
            SpanStyle(
                color = Color.Green,
                fontSize = 30.sp
            ),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = "URL",
            annotation = linkText,
            start = startIndex,
            end = endIndex
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        text = annotatedString,
        onClick = {offset->
            val uri = annotatedString.getStringAnnotations("URL",offset,offset).firstOrNull()?.item

            if(uri!=null){
                uriHandler.openUri(uri)
            }

        }
    )
}

@Composable
fun Hyperlink2(
    fullText: String,
    linkText: String
){
    val annotatedString = buildAnnotatedString {
        append(fullText)

        val startIndex = fullText.indexOf("DOWNLOAD")
        val endIndex = startIndex + "DOWNLOAD".length

        addStyle(
            SpanStyle(
                color = Color.Green,
                fontSize = 30.sp
            ),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = "DOWNLOAD",
            annotation = linkText,
            start = startIndex,
            end = endIndex
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        text = annotatedString,
        onClick = {offset->
            val uri = annotatedString.getStringAnnotations("DOWNLOAD",offset,offset).firstOrNull()?.item

            if(uri!=null){
                uriHandler.openUri(uri)
            }

        }
    )
}