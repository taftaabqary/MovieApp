package com.althaaf.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.althaaf.movieapp.model.Movie
import com.althaaf.movieapp.model.getMovies
import com.althaaf.movieapp.navigation.MovieNavigation
import com.althaaf.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    MovieAppTheme {
        content()
    }
}

@Preview
@Composable
fun RowItem(
    modifier: Modifier = Modifier,
    item: Movie = getMovies()[0],
    onClickItem: () -> Unit = {}
) {
    var expandState by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                onClickItem()
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 14.dp, vertical = 12.dp)
                    .size(150.dp),
                shape = RoundedCornerShape(12.dp),
                shadowElevation = 4.dp,
                color = Color.White,
            ) {
                AsyncImage(
                    model = item.images[0],
                    contentDescription = "Image Movie",
                )
            }
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    item.title,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    "Director : ${item.director}",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray)
                )
                Text(
                    "Released : ${item.year}",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray)
                )
                AnimatedVisibility(expandState) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 14.sp,
                                    color = Color.DarkGray,
                                    fontStyle = FontStyle.Normal,
                                )
                            ) {
                                append("Plot : ")
                            }

                            withStyle(
                                style = SpanStyle(
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Light,
                                    letterSpacing = MaterialTheme.typography.bodyMedium.letterSpacing
                                )
                            ) {
                                append(item.plot)
                            }
                        }, modifier = Modifier.padding(horizontal = 8.dp))
                        HorizontalDivider(
                            modifier = Modifier.padding(
                                horizontal = 4.dp,
                                vertical = 2.dp
                            ), color = Color.LightGray, thickness = 1.dp
                        )
                        Text("Director : ${item.director}", style = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray))
                        Text("Actors : ${item.actors}", style = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray))
                        Text("Rating : ${item.rating}", style = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray))
                    }
                }
                Icon(
                    imageVector = if (expandState) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Arrow Expand",
                    modifier = Modifier.clickable {
                        expandState = !expandState
                    }
                )
            }
        }
    }
}