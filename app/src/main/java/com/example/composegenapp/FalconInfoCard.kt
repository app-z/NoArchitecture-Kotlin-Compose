package com.example.composegenapp

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun FalconInfoCard(falconInfo: FalconInfo) {
    Row(modifier = Modifier.padding(all = 8.dp)) {

        AsyncImage(
            placeholder = rememberVectorPainter(Icons.Filled.Rocket),
            model = falconInfo.links?.patch?.small,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
            //.aspectRatio(0.8F)
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = "surf_color",
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded })
        {
            falconInfo.name?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                falconInfo.details?.let {
                    Text(
                        text = it,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    FalconInfoCard(
        FalconInfo(
            staticFireDateUtc = "234234234",
            staticFireDateUnix = 1,
            name = "Rocket", rocket = "N1",
            details = "Detail can be long, Detail can be long, Detail can be long, "
        )
    )
}
