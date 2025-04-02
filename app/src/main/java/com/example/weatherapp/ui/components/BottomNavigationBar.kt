package com.example.weatherapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults.IconSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.navigation.NavigationRoute

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    onItemClick: (Int) -> Unit,
    selectedItem: NavigationRoute?
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color(0xFF222A36),
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == item.route,
                onClick = {onItemClick(index)},
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier.size(IconSize)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = item.title, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color(0xFF9396A6),
                    unselectedTextColor = Color(0xFF9396A6)
                )

            )
        }
    }
}

data class BottomNavItem(
    val route: NavigationRoute,
    @DrawableRes val icon: Int,
    val title: String
)