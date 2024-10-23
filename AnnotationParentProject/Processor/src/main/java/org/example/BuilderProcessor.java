package org.example;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AutoService(Processor.class)
@SupportedAnnotationTypes("org.example.Builder")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class BuilderProcessor extends AbstractProcessor {

//kompilator kompiluje w rundach

    /*
    kompiluje wszystkei pliki, ktore masz, ktore maja anotacje. Wywoluje procesor do obslugi anotacji i np. sa generowane nowe pliki itp
    nastepnie kompilator kompiluje nowo powstale pliki np. z klasami i sprawdza czy maja jakies anotacje
    jezeli tak to wywoluje znowu procesor do anotacji
    Summary of What Happens in Each Round:
Round 1:

The processor processes all the elements in the source code.
If the processor generates new code, it will be compiled for the next round.
Round 2 (if new code is generated):

The processor processes newly generated elements.
If more code is generated, another round is initiated.
Final Round:

No more new code is generated, and the processor is called for the last time with no new elements to process.

     */
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.WARNING,"=================================================================================");
    }
//annotations zawiera set wszytskich anotaji znalezionch
// (te ktore sa obslugiwane przez ten procesor)
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        for (TypeElement annotation : annotations) {
            roundEnv.getElementsAnnotatedWith(annotation)
                    .forEach(this::generateBuilderFile);
        }
        return true;
    }

    private void generateBuilderFile(Element element) {
        String className = element.getSimpleName().toString();
        String packageName = element.getEnclosingElement().toString();
        String builderName = className + "Builder";
        String builderFullName = packageName + "." + builderName;

        List<? extends Element> fields = element.getEnclosedElements().stream()
                .filter(e -> e.getKind().equals(ElementKind.FIELD)).toList();
        for (Element field : fields) {
           messager.printNote("===========================================================================================================");
            messager.printMessage(Diagnostic.Kind.NOTE, "Field: " + field.getSimpleName() + " of type: " + field.asType());
        }

        try (PrintWriter writer = new PrintWriter(
                processingEnv.getFiler().createSourceFile(builderFullName).openWriter())) {
            writer.println("""
                    package %s;                  
                    public class %s{
                    """.formatted(packageName, builderName));

            fields.forEach(field ->
                    writer.print("""
                        private %s %s;
                        """.formatted(
                                    field.asType().toString(),
                                    field.getSimpleName()
                            )
                    )
            );

            writer.println();

            fields.forEach(field ->
                    writer.println("""
                            public %s %s(%s value) {
                                %s = value;
                                return this;
                            }
                            """.formatted(
                                    builderName,
                                    field.getSimpleName(),
                                    field.asType().toString(),
                                    field.getSimpleName()
                            )
                    )
            );

            writer.println("""
                    public %s build() {
                        return new %s(%s);
                        }
                    """.formatted(
                            className,
                            className,
                            fields.stream().map(Element::getSimpleName).collect(Collectors.joining(", ")
                            )
                    )
            );
            writer.println("}");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

