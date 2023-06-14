package com.example.themeapp.ui.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.themeapp.data.AppDataStore
import com.example.themeapp.ui.navigation.data.ScreenDataModel
import com.example.themeapp.model.CustomTheme
import com.example.themeapp.ui.theme.customColors
import kotlinx.coroutines.launch

@Composable
fun ScreenA(screenDataModel: ScreenDataModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.customColors.backgroundColor)) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .wrapContentHeight(align = Alignment.CenterVertically)
            .padding(all = 4.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColors.textColor,
            text = stringResource(id = screenDataModel.textId ?: 0)
        )
    }
}

@Composable
fun ScreenB(screenDataModel: ScreenDataModel,
            dataStore: AppDataStore) {
    val coroutineScope = rememberCoroutineScope()
    var menuExpanded by remember { mutableStateOf(false) }
    val menuItems = mutableListOf<CustomTheme>().apply {
        add(CustomTheme.A)
        add(CustomTheme.B)
        add(CustomTheme.C)
        add(CustomTheme.D)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.customColors.backgroundColor)) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .wrapContentHeight()
            .padding(all = 4.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColors.textColor,
            text = stringResource(id = screenDataModel.textId ?: 0))
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .wrapContentHeight()) {
            Button(modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight()
                .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.customColors.textColor,
                    contentColor = MaterialTheme.customColors.backgroundColor),
                onClick = { menuExpanded = !menuExpanded }) {
                Text(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(all = 4.dp),
                    text = "Change Theme")
                Icon(imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null)
            }
            DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                menuItems.forEach { item ->
                    DropdownMenuItem(onClick = {
                        coroutineScope.launch {
                            dataStore.saveTheme(item)
                        }
                        menuExpanded = false
                    }) {
                        Text(text = when(item) {
                            CustomTheme.A -> "Theme A"
                            CustomTheme.B -> "Theme B"
                            CustomTheme.C -> "Theme C"
                            CustomTheme.D -> "Theme D"
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenC(screenDataModel: ScreenDataModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.customColors.backgroundColor)) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .wrapContentHeight(align = Alignment.CenterVertically)
            .padding(all = 4.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColors.textColor,
            text = stringResource(id = screenDataModel.textId ?: 0)
        )
    }
}

@Preview
@Composable
fun ScreenAPreview() {
    ScreenA(screenDataModel = ScreenDataModel())
}

@Preview
@Composable
fun ScreenBPreview() {
    //ScreenB(screenDataModel = ScreenDataModel(), dataStore = dataStore)
}

@Preview
@Composable
fun ScreenCPreview() {
    ScreenC(screenDataModel = ScreenDataModel())
}