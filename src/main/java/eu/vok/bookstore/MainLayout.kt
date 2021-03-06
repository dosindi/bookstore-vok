package eu.vok.bookstore

import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.flexLayout
import com.vaadin.flow.component.dependency.CssImport
import com.vaadin.flow.component.dependency.StyleSheet
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.FlexLayout
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.RouterLayout
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.lumo.Lumo
import eu.vaadinonkotlin.vaadin10.Session
import eu.vaadinonkotlin.vaadin10.VokSecurity
import eu.vok.bookstore.about.AboutView
import eu.vok.bookstore.authentication.LoginView
import eu.vok.bookstore.authentication.loginManager
import eu.vok.bookstore.crud.SampleCrudView

/**
 * The layout of the pages e.g. About and Inventory.
 */
@CssImport("./css/shared-styles.css")
@Theme(value = Lumo::class, variant = Lumo.DARK)
class MainLayout : KComposite(), RouterLayout, BeforeEnterObserver {
    override fun beforeEnter(event: BeforeEnterEvent) {
        if (!Session.loginManager.isLoggedIn) {
            event.rerouteTo(LoginView::class.java)
        } else {
            VokSecurity.checkPermissionsOfView(event.navigationTarget)
        }
    }

    private val root = ui {
        flexLayout {
            setSizeFull(); className = "main-layout"

            menu {
                addView(SampleCrudView::class, SampleCrudView.VIEW_NAME, VaadinIcon.EDIT)
                addView(AboutView::class, AboutView.VIEW_NAME, VaadinIcon.INFO_CIRCLE)
            }
        }
    }
}

