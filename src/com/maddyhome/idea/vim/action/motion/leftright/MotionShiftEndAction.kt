/*
 * IdeaVim - Vim emulator for IDEs based on the IntelliJ platform
 * Copyright (C) 2003-2019 The IdeaVim authors
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.maddyhome.idea.vim.action.motion.leftright

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.maddyhome.idea.vim.VimPlugin
import com.maddyhome.idea.vim.action.VimCommandAction
import com.maddyhome.idea.vim.command.Command
import com.maddyhome.idea.vim.command.CommandFlags
import com.maddyhome.idea.vim.command.MappingMode
import com.maddyhome.idea.vim.group.MotionGroup
import com.maddyhome.idea.vim.handler.ShiftedSpecialKeyHandler
import com.maddyhome.idea.vim.helper.enumSetOf
import com.maddyhome.idea.vim.helper.inInsertMode
import com.maddyhome.idea.vim.helper.inSelectMode
import com.maddyhome.idea.vim.helper.inVisualMode
import com.maddyhome.idea.vim.helper.vimForEachCaret
import com.maddyhome.idea.vim.helper.vimLastColumn
import com.maddyhome.idea.vim.option.OptionsManager
import java.util.*
import javax.swing.KeyStroke

class MotionShiftEndAction : VimCommandAction() {
  override fun makeActionHandler() = object : ShiftedSpecialKeyHandler() {
    override fun motion(editor: Editor, context: DataContext, cmd: Command) {
      editor.vimForEachCaret { caret ->
        var allow = false
        if (editor.inInsertMode) {
          allow = true
        } else if (editor.inVisualMode || editor.inSelectMode) {
          val opt = OptionsManager.selection
          if (opt.value != "old") {
            allow = true
          }
        }

        val newOffset = VimPlugin.getMotion().moveCaretToLineEndOffset(editor, caret, cmd.count - 1, allow)
        caret.vimLastColumn = MotionGroup.LAST_COLUMN
        MotionGroup.moveCaret(editor, caret, newOffset)
        caret.vimLastColumn = MotionGroup.LAST_COLUMN
      }
    }
  }

  override val mappingModes: MutableSet<MappingMode> = MappingMode.NVS

  override val keyStrokesSet: Set<List<KeyStroke>> = parseKeysSet("<S-End>")

  override val type: Command.Type = Command.Type.MOTION

  override val flags: EnumSet<CommandFlags> = enumSetOf(CommandFlags.FLAG_MOT_EXCLUSIVE)
}