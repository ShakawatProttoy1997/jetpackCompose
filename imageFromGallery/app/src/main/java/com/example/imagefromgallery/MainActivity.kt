package com.example.imagefromgallery

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.example.imagefromgallery.ui.theme.ImageFromGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageFromGalleryTheme {
                // A surface container using the 'background' color from the theme
                goToMain()
            }
        }
    }


}
@Composable
fun goToMain()
{
    var selectedImage by remember{mutableStateOf<Uri?>(null)}
val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
    uri -> selectedImage = uri
}
    mainAppContent(selectedImage) {
            launcher.launch("image/*")

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun mainAppContent(selectedImage: Uri? = null,
                           onImageClick: ()->Unit)
{
        Scaffold(){
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                 if(selectedImage != null){
                     Image(painter = rememberImagePainter(selectedImage),
                         contentDescription = "Selected Image", modifier = Modifier
                             .fillMaxSize()
                             .clickable {
                                 onImageClick()
                             })
                     Text(text = "found...")
                 }
                else OutlinedButton(onClick = onImageClick) {
                     Text(text = "pick your favorite image...")
                 }
                    
                 
            }
        }
}
@Preview
@Composable
private fun preview(){
    ImageFromGalleryTheme(){
        mainAppContent() {

        }
    }
}
@Preview
@Composable
private fun newPreviewForSelectedImage(){
    ImageFromGalleryTheme(){
        mainAppContent(Uri.EMPTY) {

        }
    }
}


