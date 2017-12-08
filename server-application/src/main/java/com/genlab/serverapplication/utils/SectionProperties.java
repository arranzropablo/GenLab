package com.genlab.serverapplication.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
@ConfigurationProperties(prefix="genlab.sections")
public class SectionProperties {
	private List<LinkSectionProperties> links = new ArrayList<>();
}
