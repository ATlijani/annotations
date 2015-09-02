package com.atlwork;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

public class CodeGen {

	private CodeGen() {
	}

	public static PackageElement getPackage(Element type) {
		while (type.getKind() != ElementKind.PACKAGE) {
			type = type.getEnclosingElement();
		}
		return (PackageElement) type;
	}

	/**
	 * Returns a fully qualified class name to complement {@code type}.
	 */
	public static String adapterName(TypeElement typeElement, String suffix) {
		StringBuilder builder = new StringBuilder();
		rawTypeToString(builder, typeElement, '$');
		builder.append(suffix);
		return builder.toString();
	}

	static void rawTypeToString(StringBuilder result, TypeElement type, char innerClassSeparator) {
		System.out.println("@ATL : innerClassSep =" + innerClassSeparator);
		String packageName = getPackage(type).getQualifiedName().toString();
		String qualifiedName = type.getQualifiedName().toString();
		result.append(packageName);
		result.append('.');
		result.append(qualifiedName.substring(packageName.length() + 1).replace('.',
				innerClassSeparator));
		System.out.println("@ATL : " + result.toString());
	}
}
