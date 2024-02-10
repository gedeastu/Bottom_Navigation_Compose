package com.example.bottomnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.ui.theme.BottomNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                       bottomBar =  {
                           BottomNavigationBar(
                               items = listOf(
                                   BottomNavItem(
                                       name = "Home",
                                       route = "home",
                                       icon = Icons.Default.Home,
                                   ),
                                   BottomNavItem(
                                       name = "Chat",
                                       route = "chat",
                                       icon = Icons.Default.Notifications,
                                       badgeCount = 23
                                   ),
                                   BottomNavItem(
                                       name = "Settings",
                                       route = "settings",
                                       icon = Icons.Default.Settings,
                                   )
                                ),
                               navController = navController,
                               onItemClick = {
                                    navController.navigate(it.route)
                               })
                       }
                    ) { paddingValues ->
                        Navigation(modifier = Modifier.padding(paddingValues),navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(modifier: Modifier = Modifier,navController:NavHostController) {
    NavHost(navController = navController, startDestination = "home"){
        composable(route = "home"){
            HomeScreen()
        }
        composable(route = "chat"){
            ChatScreen()
        }
        composable(route = "settings"){
            SettingsScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(modifier = modifier.background(Color.DarkGray), tonalElevation = 5.dp) {
        items.forEach{item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onItemClick(item)
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        if(item.badgeCount > 0){
                            BadgedBox(badge = {
                                Text(text = item.badgeCount.toString())
                            }) {
                                Icon(imageVector = item.icon, contentDescription = item.name)
                            }
                        }else{
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        }
                        if (selected){
                            Text(text = item.name, textAlign = TextAlign.Center, fontSize = 10.sp)
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Green,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Home Screen")
    }
}

@Composable
fun ChatScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Chat Screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Settings Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomNavigationTheme {

    }
}