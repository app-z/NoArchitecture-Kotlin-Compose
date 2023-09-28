# NoArchitecture-Kotlin-Compose
NoArchitecture Kotlin Compose


                    var selectedTabIndex by remember {
                        mutableIntStateOf(0)
                    }


                    LaunchedEffect(selectedTabIndex) {
                        pagerState.animateScrollToPage(selectedTabIndex)
                    }


                    
