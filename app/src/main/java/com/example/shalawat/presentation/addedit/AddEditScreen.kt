package com.example.shalawat.presentation.addedit

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shalawat.presentation.theme.EmeraldGreen
import com.example.shalawat.presentation.theme.GoldenYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    onNavigateBack: () -> Unit,
    onSaved: () -> Unit,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val formState by viewModel.formState.collectAsStateWithLifecycle()
    val isEditMode = viewModel.isEditMode
    val snackbarHostState = remember { SnackbarHostState() }

    val audioPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.onAudioFilePicked(it) }
    }

    // Show error in snackbar
    LaunchedEffect(formState.error) {
        formState.error?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.clearError()
        }
    }

    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = EmeraldGreen,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
        focusedLabelColor = EmeraldGreen,
        cursorColor = EmeraldGreen
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isEditMode) "Edit Shalawat" else "Add New Shalawat",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->

        if (formState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = EmeraldGreen)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                // ── Shalawat Title ──
                FieldLabel("SHALAWAT TITLE")
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = formState.title,
                    onValueChange = viewModel::onTitleChange,
                    placeholder = { Text("e.g. Shalawat Ibrahimiyah", fontSize = 14.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = fieldColors
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ── Arabic Text ──
                FieldLabel("ARABIC TEXT")
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = formState.arabicText,
                    onValueChange = viewModel::onArabicTextChange,
                    placeholder = { Text("اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ...", fontSize = 14.sp) },
                    minLines = 3,
                    maxLines = 5,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = fieldColors
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Tip: Use calligraphy style texts for better spiritual aesthetics.",
                    fontSize = 11.sp,
                    fontStyle = FontStyle.Italic,
                    color = EmeraldGreen.copy(alpha = 0.6f),
                    modifier = Modifier.padding(start = 4.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ── Transliteration ──
                FieldLabel("TRANSLITERATION")
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = formState.transliteration,
                    onValueChange = viewModel::onTransliterationChange,
                    placeholder = { Text("Allahumma salli 'ala Muhammad...", fontSize = 14.sp) },
                    minLines = 2,
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = fieldColors
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ── Translation ──
                FieldLabel("TRANSLATION")
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = formState.translation,
                    onValueChange = viewModel::onTranslationChange,
                    placeholder = { Text("O Allah, bestow Your blessings upon Muhammad...", fontSize = 14.sp) },
                    minLines = 2,
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = fieldColors
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ── Recitation Audio ──
                FieldLabel("RECITATION AUDIO")
                Spacer(modifier = Modifier.height(8.dp))

                // Dashed border upload area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(EmeraldGreen.copy(alpha = 0.05f))
                        .clickable { audioPickerLauncher.launch("audio/*") },
                    contentAlignment = Alignment.Center
                ) {
                    // Draw dashed border
                    Canvas(modifier = Modifier.matchParentSize()) {
                        val dashPathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(12f, 8f), 0f
                        )
                        drawRoundRect(
                            color = Color(0xFF2D6A4F).copy(alpha = 0.4f),
                            style = Stroke(
                                width = 2f,
                                pathEffect = dashPathEffect
                            ),
                            cornerRadius = CornerRadius(12.dp.toPx())
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Filled.MusicNote,
                            contentDescription = null,
                            tint = EmeraldGreen.copy(alpha = 0.6f),
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        if (formState.audioFileName.isNotBlank()) {
                            Text(
                                text = formState.audioFileName,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = EmeraldGreen,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            Text(
                                text = "Tap to upload audio recitation",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "MP3, WAV, or M4A (Max 50MB)",
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // ── Save Button (Golden) ──
                Button(
                    onClick = { viewModel.save(onComplete = onSaved) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    enabled = !formState.isSaving && formState.isValid,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GoldenYellow,
                        contentColor = Color(0xFF3D3005),
                        disabledContainerColor = GoldenYellow.copy(alpha = 0.4f),
                        disabledContentColor = Color(0xFF3D3005).copy(alpha = 0.5f)
                    )
                ) {
                    if (formState.isSaving) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color(0xFF3D3005),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            text = "★  ${if (isEditMode) "Update Shalawat" else "Save Shalawat"}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Subtitle text below save button
                Text(
                    text = if (isEditMode) "Changes will be saved to your collection."
                    else "This Shalawat will be added to your private collection.",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

// ── Uppercase Field Label ──

@Composable
private fun FieldLabel(label: String) {
    Text(
        text = label,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        letterSpacing = 1.5.sp
    )
}
