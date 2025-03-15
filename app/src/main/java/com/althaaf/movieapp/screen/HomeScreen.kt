package com.althaaf.movieapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.althaaf.movieapp.RowItem
import com.althaaf.movieapp.model.Movie
import com.althaaf.movieapp.model.getMovies
import com.althaaf.movieapp.navigation.MovieScreen
import com.althaaf.movieapp.ui.theme.MovieAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    MovieAppTheme {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = Modifier,
                    title = {
                        Text("Movie List")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Magenta)

                )
            }
        ) { innerPadding ->
            MainContent(Modifier.padding(innerPadding), navController = navController)
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier, listMovie: List<Movie> = getMovies(), navController: NavController
) {
    Column(modifier = modifier.padding(horizontal = 12.dp)) {
        LazyColumn {
            items(listMovie) {
                RowItem(item = it, onClickItem = {
                    navController.navigate(MovieScreen.DETAIL.name+"/${it.id}")
                })
            }
        }
    }
}