package com.ewersson.netflix_api_app.service.exception

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ObjectNotFoundException(message: String?) : EntityNotFoundException(message)