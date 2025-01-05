@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package educateyou.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi

private object CommonMainDrawable0 {
  public val compose_multiplatform: DrawableResource by 
      lazy { init_compose_multiplatform() }

  public val ic_authentication_closed_eye: DrawableResource by 
      lazy { init_ic_authentication_closed_eye() }

  public val ic_authentication_opened_eye: DrawableResource by 
      lazy { init_ic_authentication_opened_eye() }

  public val ic_calendar_no_plans: DrawableResource by 
      lazy { init_ic_calendar_no_plans() }

  public val logo: DrawableResource by 
      lazy { init_logo() }

  public val profile: DrawableResource by 
      lazy { init_profile() }
}

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("compose_multiplatform", CommonMainDrawable0.compose_multiplatform)
  map.put("ic_authentication_closed_eye", CommonMainDrawable0.ic_authentication_closed_eye)
  map.put("ic_authentication_opened_eye", CommonMainDrawable0.ic_authentication_opened_eye)
  map.put("ic_calendar_no_plans", CommonMainDrawable0.ic_calendar_no_plans)
  map.put("logo", CommonMainDrawable0.logo)
  map.put("profile", CommonMainDrawable0.profile)
}

internal val Res.drawable.compose_multiplatform: DrawableResource
  get() = CommonMainDrawable0.compose_multiplatform

private fun init_compose_multiplatform(): DrawableResource =
    org.jetbrains.compose.resources.DrawableResource(
  "drawable:compose_multiplatform",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/educateyou.composeapp.generated.resources/drawable/compose-multiplatform.xml", -1, -1),
    )
)

internal val Res.drawable.ic_authentication_closed_eye: DrawableResource
  get() = CommonMainDrawable0.ic_authentication_closed_eye

private fun init_ic_authentication_closed_eye(): DrawableResource =
    org.jetbrains.compose.resources.DrawableResource(
  "drawable:ic_authentication_closed_eye",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/educateyou.composeapp.generated.resources/drawable/ic_authentication_closed_eye.xml", -1, -1),
    )
)

internal val Res.drawable.ic_authentication_opened_eye: DrawableResource
  get() = CommonMainDrawable0.ic_authentication_opened_eye

private fun init_ic_authentication_opened_eye(): DrawableResource =
    org.jetbrains.compose.resources.DrawableResource(
  "drawable:ic_authentication_opened_eye",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/educateyou.composeapp.generated.resources/drawable/ic_authentication_opened_eye.xml", -1, -1),
    )
)

internal val Res.drawable.ic_calendar_no_plans: DrawableResource
  get() = CommonMainDrawable0.ic_calendar_no_plans

private fun init_ic_calendar_no_plans(): DrawableResource =
    org.jetbrains.compose.resources.DrawableResource(
  "drawable:ic_calendar_no_plans",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/educateyou.composeapp.generated.resources/drawable/ic_calendar_no_plans.xml", -1, -1),
    )
)

internal val Res.drawable.logo: DrawableResource
  get() = CommonMainDrawable0.logo

private fun init_logo(): DrawableResource = org.jetbrains.compose.resources.DrawableResource(
  "drawable:logo",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/educateyou.composeapp.generated.resources/drawable/logo.png", -1, -1),
    )
)

internal val Res.drawable.profile: DrawableResource
  get() = CommonMainDrawable0.profile

private fun init_profile(): DrawableResource = org.jetbrains.compose.resources.DrawableResource(
  "drawable:profile",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/educateyou.composeapp.generated.resources/drawable/profile.png", -1, -1),
    )
)
