package the.store.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DecimalVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val newText = StringBuilder()
        var decimalCount = 0
        for (char in text) {
            if (char == '.' && decimalCount == 3) {
                continue
            }
            if (char == '.' && decimalCount < 3) {
                decimalCount++
            }
            newText.append(char)
        }
        return TransformedText(AnnotatedString(newText.toString()), OffsetMapping.Identity)
    }
}