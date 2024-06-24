const sidenav = document.getElementById("main-sidenav");

const sidenavInstance = mdb.Sidenav.getInstance(sidenav);

const currencyFormat = Intl.NumberFormat("vi-VN", {style: 'currency', currency: 'VND'})


let innerWidth = null;

//

const setMode = (e) => {
    // Check necessary for Android devices
    if (window.innerWidth === innerWidth) {
        return;
    }

    innerWidth = window.innerWidth;

    if (window.innerWidth < 1400) {
        sidenavInstance.changeMode("over");
        sidenavInstance.hide();
    } else {
        sidenavInstance.changeMode("side");
        sidenavInstance.show();
    }
};

setMode();

// Event listeners
window.addEventListener("resize", setMode);

const searchFocus = document.getElementById('search-focus');
const keys = [
    {keyCode: 'AltLeft', isTriggered: false},
    {keyCode: 'ControlLeft', isTriggered: false},
];

window.addEventListener('keydown', (e) => {
    keys.forEach((obj) => {
        if (obj.keyCode === e.code) {
            obj.isTriggered = true;
        }
    });

    const shortcutTriggered = keys.filter((obj) => obj.isTriggered).length === keys.length;

    if (shortcutTriggered) {
        searchFocus.focus();
    }
});

window.addEventListener('keyup', (e) => {
    keys.forEach((obj) => {
        if (obj.keyCode === e.code) {
            obj.isTriggered = false;
        }
    });
});

