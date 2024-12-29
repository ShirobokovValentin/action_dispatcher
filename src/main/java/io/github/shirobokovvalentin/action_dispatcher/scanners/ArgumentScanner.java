package io.github.shirobokovvalentin.action_dispatcher.scanners;

import io.github.shirobokovvalentin.convertors.ParamConvertor;
import io.github.shirobokovvalentin.action_dispatcher.constraint.ParamConstraintScanner;
import io.github.shirobokovvalentin.action_dispatcher.constraint.validators.ComplexValidator;
import io.github.shirobokovvalentin.action_dispatcher.entity.ArgumentDescriptor;
import io.github.shirobokovvalentin.action_dispatcher.exceptions.IncompatibleAnnotationException;
import io.github.shirobokovvalentin.action_dispatcher.exceptions.InvalidMappingException;
import io.github.shirobokovvalentin.action_dispatcher.exceptions.RequredAnnotationMissingException;
import io.github.shirobokovvalentin.action_dispatcher.readers.AttrReader;
import io.github.shirobokovvalentin.action_dispatcher.readers.ParamReader;
import io.github.shirobokovvalentin.action_dispatcher.readers.RawRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

public class ArgumentScanner
{
	private final ParamReaderScanner paramReaderScanner = new ParamReaderScanner();

	private final ParamConvertorScanner convertorScanner = new ParamConvertorScanner();

	private final ParamConstraintScanner constraintsScanner = new ParamConstraintScanner();

	private final AttributeReaderScanner attributeReaderScanner = new AttributeReaderScanner();

	// ------------------------------------------------------------

	public ArgumentDescriptor scan(Annotation[] annotations, Class<?> argumentType) throws InvalidMappingException
	{

		if (argumentType == HttpServletRequest.class)
		{
			AttributeArgumentDescriptor argDescriptor = new AttributeArgumentDescriptor();
			argDescriptor.setAttrReader(new RawRequest());
			argDescriptor.setConstraint(new ComplexValidator());
			return argDescriptor;
		}

		ComplexValidator constraint = constraintsScanner.scan(annotations, argumentType);
		ParamReader paramReader = paramReaderScanner.scan(annotations, argumentType);
		AttrReader attrReader = attributeReaderScanner.scan(annotations, argumentType);

		if (paramReader != null && attrReader != null)
		{
			throw new IncompatibleAnnotationException();
		}

		if (paramReader != null)
		{
			ParamConvertor<?> paramConvertor = convertorScanner.scan(annotations, argumentType);

			ParamArgumentDescriptor argDescriptor = new ParamArgumentDescriptor();
			argDescriptor.setParamReader(paramReader);
			argDescriptor.setParamConvertor(paramConvertor);
			argDescriptor.setConstraint(constraint);
			return argDescriptor;
		}

		if (attrReader != null)
		{
			AttributeArgumentDescriptor argDescriptor = new AttributeArgumentDescriptor();
			argDescriptor.setAttrReader(attrReader);
			argDescriptor.setConstraint(constraint);
			return argDescriptor;
		}

		throw new RequredAnnotationMissingException()
				.annotation("@ParamReaderClass")
				.annotation("@AttributeReaderClass");
	}

}