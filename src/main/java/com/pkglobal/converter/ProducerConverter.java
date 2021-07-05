package com.pkglobal.converter;

public interface ProducerConverter<I, O> {

    O convert(I input);

}
