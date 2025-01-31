@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package educateyou.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.InternalResourceApi

private object CommonMainFont0 {
  public val Bold: FontResource by 
      lazy { init_Bold() }

  public val Thin: FontResource by 
      lazy { init_Thin() }
}

@InternalResourceApi
internal fun _collectCommonMainFont0Resources(map: MutableMap<String, FontResource>) {
  map.put("Bold", CommonMainFont0.Bold)
  map.put("Thin", CommonMainFont0.Thin)
}

internal val Res.font.Bold: FontResource
  get() = CommonMainFont0.Bold

private fun init_Bold(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:Bold",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/educateyou.composeapp.generated.resources/font/Bold.otf", -1, -1),
    )
)

internal val Res.font.Thin: FontResource
  get() = CommonMainFont0.Thin

private fun init_Thin(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:Thin",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/educateyou.composeapp.generated.resources/font/Thin.otf", -1, -1),
    )
)
