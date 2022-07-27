import java.io.IOException;
import java.nio.file.Paths;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
public class Generate {

    public void generateJava() {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("com.example", helloWorld)
                .addFileComment("Hi HelloWorld class ")
                .build();

        try {
            javaFile.writeTo(Paths.get("./src/main/java"));
        }
        catch (IOException ex){
            System.out.println("exceptio" + ex.getMessage());
        }
    }
}
