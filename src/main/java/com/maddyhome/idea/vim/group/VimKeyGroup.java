/*
 * IdeaVim - Vim emulator for IDEs based on the IntelliJ platform
 * Copyright (C) 2003-2022 The IdeaVim authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.maddyhome.idea.vim.group;

import com.maddyhome.idea.vim.common.MappingMode;
import com.maddyhome.idea.vim.common.CommandPartNode;
import com.maddyhome.idea.vim.key.KeyMapping;
import com.maddyhome.idea.vim.key.KeyMappingLayer;
import com.maddyhome.idea.vim.newapi.VimActionsInitiator;
import org.jetbrains.annotations.NotNull;

public interface VimKeyGroup {
  @NotNull KeyMapping getKeyMapping(@NotNull MappingMode mode);

  @NotNull CommandPartNode<VimActionsInitiator> getKeyRoot(@NotNull MappingMode mappingMode);

  @NotNull KeyMappingLayer getKeyMappingLayer(@NotNull MappingMode mode);
}
