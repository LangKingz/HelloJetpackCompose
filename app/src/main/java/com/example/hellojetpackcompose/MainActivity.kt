package com.example.hellojetpackcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hellojetpackcompose.Component.MyTopBar
import com.example.hellojetpackcompose.ui.theme.HelloJetpackComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloJetpackComposeTheme(
                dynamicColor = false
            ) {
                HellojetPackComposeApp()
            }
        }
    }
}

val itemmenu = listOf(
    com.example.hellojetpackcompose.Model.MenuItem(
        title = "Home",
        icon = androidx.compose.material.icons.Icons.Outlined.Home
    ),
    com.example.hellojetpackcompose.Model.MenuItem(
        title = "Profile",
        icon = androidx.compose.material.icons.Icons.Outlined.Person
    )
)

@Composable
fun HellojetPackComposeApp(viewmodel: MainViewmodel = MainViewmodel()) {
    val listData by viewmodel.listdata.observeAsState(initial = emptyList())
    val loading by viewmodel.loading.observeAsState(initial = false)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val snackBarhost = remember { SnackbarHostState() }
    val context = LocalContext.current



    val selectedMenu = remember { mutableStateOf(itemmenu[0]) }

    LaunchedEffect(Unit) {
        viewmodel.fetch()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackBarhost)
        },
        topBar = {
            MyTopBar(onMenuClick = {
                scope.launch {
                    if(drawerState.isClosed){
                        drawerState.open()
                    }else{
                        drawerState.close()
                    }
                }
            })
        },
        bottomBar = {

        }
    ) { innerPadding ->
        ModalNavigationDrawer(
            modifier = Modifier.padding(innerPadding),
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(Modifier.height(10.dp))
                    itemmenu.map { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = "") },
                            label = { Text(item.title) },
                            selected = item == selectedMenu.value,
                            onClick = {
                                selectedMenu.value = item
                                scope.launch {
                                    drawerState.close()
                                    snackBarhost.showSnackbar(
                                        message = "selectedItem : ${item.title}",
                                        withDismissAction = true,
                                        actionLabel = "cek"
                                    )
                                }
                            },
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                }
            },
            content = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        if(drawerState.isClosed){
                            "Drawer Closed"
                        }else{
                            "Drawer Open"
                        }
                    )
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    HelloJetpackComposeTheme(
        dynamicColor = false
    ) {
        HellojetPackComposeApp()
    }
}

