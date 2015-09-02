package com.atlwork;

import static java.lang.reflect.Modifier.FINAL;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("com.atlwork.GeneratedTypeAdapter")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public final class GeneratedTypeAdapterProcessor extends AbstractProcessor {
	@Override
	public boolean process(Set<? extends TypeElement> types, RoundEnvironment env) {
		System.out.println("invoked GeneratedTypeAdapterProcessor");
		try {
			for (Element element : env.getElementsAnnotatedWith(GeneratedTypeAdapter.class)) {
				writeAdapter((TypeElement) element);
			}
		} catch (IOException e) {
			processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
		}
		return true;
	}

	private void writeAdapter(TypeElement type) throws IOException {
		String typeAdapterName = CodeGen.adapterName(type, "$TypeAdapter");
		JavaFileObject sourceFile = processingEnv.getFiler()
				.createSourceFile(typeAdapterName, type);
		System.out.println("Generating type adapter: " + typeAdapterName + " in "
				+ sourceFile.getName());

		JavaWriter writer = new JavaWriter(sourceFile.openWriter());
		writer.addPackage(CodeGen.getPackage(type).getQualifiedName().toString());
		writer.beginType(typeAdapterName, "class", FINAL, null);
		writer.endType();
		writer.close();
	}
}
