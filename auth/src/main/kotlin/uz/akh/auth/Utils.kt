package uz.akh.auth

import org.springframework.security.core.context.SecurityContextHolder
import uz.akh.auth.getUserId

fun getUserId() = SecurityContextHolder.getContext().getUserId()

fun currentUserName() = SecurityContextHolder.getContext().authentication.principal as String
