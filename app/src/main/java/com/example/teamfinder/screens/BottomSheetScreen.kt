import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val itemsList = (0..5).toList()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(sheetState: SheetState, scope: CoroutineScope) {

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
            }
        },
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(vertical = 4.dp, horizontal = 16.dp)
        ) {
            item {
                Row(
                    Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .height(144.dp)
                            .clip(shape = RoundedCornerShape(24.dp))
                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                            .padding(16.dp),
                    ) {
                        Row(Modifier.fillMaxWidth()) {
                            Box(
                                Modifier
                                    .size(112.dp)
                                    .clip(shape = RoundedCornerShape(24.dp))
                                    .background(color = MaterialTheme.colorScheme.error)
                            )
                            Box(Modifier.size(12.dp))
                            Row(
                                Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(Modifier.weight(1f)) {
                                    Text(
                                        text = "Разработчик",
                                        overflow = TextOverflow.Ellipsis,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Box(Modifier.size(8.dp))
                                    Column() {
                                        Text(
                                            text = "Требуется левел-дизайнер в нашу небольшую компанию",
                                            overflow = TextOverflow.Ellipsis,
                                            style = MaterialTheme.typography.bodySmall,
                                        )
                                        Box(Modifier.size(8.dp))
                                        Text(
                                            text = "От вас: Знание Uity",
                                            Modifier.weight(1f),
                                            overflow = TextOverflow.Ellipsis,
                                            style = MaterialTheme.typography.bodySmall,
                                        )
                                    }
                                }
                                Box(Modifier.size(8.dp))
                                Column(
                                    Modifier
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Text(
                                        text = "3.4км",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Box(
                                        Modifier
                                            .size(48.dp)
                                            .clip(shape = RoundedCornerShape(16.dp))
                                            .background(MaterialTheme.colorScheme.secondary),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Build,
                                            contentDescription = "Game",
                                            tint = MaterialTheme.colorScheme.onSecondary
                                        )

                                    }


                                }
                            }
                        }
                    }
                }
            }
            item {
                Box(modifier = Modifier.height(16.dp))
                FilledTonalButton(
                    onClick = {},
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Отозваться", style = MaterialTheme.typography.labelLarge)
                }
            }
            item {
                Box(modifier = Modifier.height(16.dp))
                Text(text = "Описание", style = MaterialTheme.typography.titleLarge)
                Box(modifier = Modifier.height(8.dp))
                Text(
                    text = "Наша небольшая компания приглашает на работу дизайнера, который будет заниматься созданием мира и проработкой уровней",
                    style = MaterialTheme.typography.bodyMedium
                )
                Box(modifier = Modifier.height(16.dp))
                Row {
                    repeat(2) {
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(height = 96.dp, width = 128.dp)
                                .clip(shape = RoundedCornerShape(24.dp))
                                .background(color = MaterialTheme.colorScheme.error)
                        )
                    }
                }
            }
            item {
                Box(modifier = Modifier.height(16.dp))
                Text(text = "Требования", style = MaterialTheme.typography.titleLarge)
                Box(modifier = Modifier.height(8.dp))
                Text(
                    text = "Знание Unity на уровне джуна\nЗнание языка программирования C# также на уровне джуна\nУмение работать в команде",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}