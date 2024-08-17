# SoftReference demo

# WeakReference demo

# PhantomReference demo

# Module demo

To get dependency's list run next in **target** folder  
`jdeps --module-path ../deps -s demo-1.0-SNAPSHOT.jar`

To build JRE only with application necessary modules run next in **target** folder  
`jlink --module-path <deps_path>:demo-1.0-SNAPSHOT.jar --add-modules com.example.demo,java.base,javafx.base,javafx.controls,javafx.fxml,javafx.graphics --output custom-jre`




