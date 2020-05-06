package org.isf.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OhWebAccessDeniedHandler implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(OhWebAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth = " + auth);
		if (auth != null) {
			logger.info(auth.getName() + " was trying to access protected resource: " + request.getRequestURI());
		}
		response.sendRedirect(request.getContextPath() + "/403");
	}
}
