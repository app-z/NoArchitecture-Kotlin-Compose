package com.example.composegenapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composegenapp.domain.model.FalconInfo

@Composable
fun FalconInfoCard(falconInfo: FalconInfo) {
        Card(modifier = Modifier
            .padding(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
                shape = RoundedCornerShape(30.dp),) {

            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
            ) {
                    Text(
                        maxLines = 1,
                        text = falconInfo.name,
                        style = MaterialTheme.typography.titleSmall
                    )
            }

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                AsyncImage(
                    placeholder = rememberVectorPainter(Icons.Filled.Rocket),
                    model = falconInfo.links?.patch?.small,
                    //imageVector = Icons.Filled.Rocket,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                    //.aspectRatio(0.8F)
                )
            }

            Spacer(modifier = Modifier
                .height(1.dp))

            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(8.dp)
            ) {
                val textDesc = if (falconInfo.details.isNullOrEmpty())
                    "No Info about mission\nEmpty description"
                else falconInfo.details
                    Text(
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        text = textDesc!!,  // Never null
                        style = MaterialTheme.typography.bodyMedium
                    )
            }
        }
}


@Preview
@Composable
fun PreviewFalconInfoCard() {
    FalconInfoCard(
        FalconInfo(
            staticFireDateUtc = "234234234",
            staticFireDateUnix = 1,
            name = "Rocket", rocket = "N1",
            details = "Detail can be long, Detail can be long, Detail can be long, "
        )
    )
}
