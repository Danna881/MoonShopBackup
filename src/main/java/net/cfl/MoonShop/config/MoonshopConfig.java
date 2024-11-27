package net.cfl.MoonShop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MoonshopConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
