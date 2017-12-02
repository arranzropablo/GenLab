package com.genlab.serverapplication.utils;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@ConfigurationProperties(prefix="genlab.sections")
public class SectionProperties {
	private List<LinkSectionProperties> links = new ArrayList<>();
}
