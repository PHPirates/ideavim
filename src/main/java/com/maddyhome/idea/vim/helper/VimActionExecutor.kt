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
package com.maddyhome.idea.vim.helper

import com.intellij.openapi.util.NlsContexts
import com.maddyhome.idea.vim.api.ExecutionContext
import com.maddyhome.idea.vim.api.VimEditor
import com.maddyhome.idea.vim.command.OperatorArguments
import com.maddyhome.idea.vim.handler.EditorActionHandlerBase
import com.maddyhome.idea.vim.newapi.NativeAction
import org.jetbrains.annotations.NonNls

interface VimActionExecutor {
  /**
   * Execute an action
   *
   * @param action  The action to execute
   * @param context The context to run it in
   */
  fun executeAction(action: NativeAction, context: ExecutionContext): Boolean

  /**
   * Execute an action by name
   *
   * @param name    The name of the action to execute
   * @param context The context to run it in
   */
  fun executeAction(name: @NonNls String, context: ExecutionContext): Boolean

  fun executeCommand(
    editor: VimEditor?,
    runnable: Runnable,
    name: @NlsContexts.Command String?,
    groupId: Any?,
  )

  fun executeEsc(context: ExecutionContext): Boolean

  fun executeVimAction(
    editor: VimEditor,
    cmd: EditorActionHandlerBase,
    context: ExecutionContext,
    operatorArguments: OperatorArguments,
  )
}
