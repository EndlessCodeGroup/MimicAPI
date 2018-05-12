/*
 * This file is part of MimicAPI.
 * Copyright (C) 2018 Osip Fatkullin
 * Copyright (C) 2018 EndlessCode Group and contributors
 *
 * MimicAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MimicAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MimicAPI.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.endlesscode.mimic.api.system.registry;

/**
 * Thrown when system hook not needed because some requirements
 * are not met.
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
public class SystemNotNeededException extends RegistryOperationException {
    /**
     * Constructs extension with no parameters.
     */
    public SystemNotNeededException() {
        super();
    }

    /**
     * Constructs extension with specified cause.
     *
     * @param cause The cause (which is saved for later retrieval by the
     *              {@link #getCause()} method). (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown)
     */
    public SystemNotNeededException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs exception with detail message.
     *
     * @param message The detail message
     */
    public SystemNotNeededException(String message) {
        super(message);
    }

    /**
     * Constructs exception with specified detail message and cause.
     *
     * @param message The detail message
     * @param cause   The cause (which is saved for later retrieval by the
     *                {@link #getCause()} method). (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     */
    public SystemNotNeededException(String message, Throwable cause) {
        super(message, cause);
    }
}
