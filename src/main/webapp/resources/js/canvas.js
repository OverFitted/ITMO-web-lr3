function initCanvas(scale=100) {
    var canvas = document.getElementById('graph');
    var ctx = canvas.getContext('2d');

    ctx.beginPath();
    ctx.moveTo(300, 0);
    ctx.lineTo(300, 600);
    ctx.moveTo(0, 300);
    ctx.lineTo(600, 300);
    ctx.strokeStyle = 'black';
    ctx.stroke();

    ctx.beginPath();
    ctx.arc(300, 300, scale * 2, Math.PI, Math.PI * 1.5, false);
    ctx.strokeStyle = 'blue';
    ctx.stroke();

    ctx.beginPath();
    ctx.rect(300, 300 - scale, scale * 2, scale);
    ctx.strokeStyle = 'green';
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(300 - scale * 2, 300);
    ctx.lineTo(300, 300);
    ctx.lineTo(300, 300 + scale * 2);
    ctx.closePath();
    ctx.strokeStyle = 'red';
    ctx.stroke();
}

function isInsideShapes(x, y, R) {
    // FIXME: no js checker issue (example.com/issues/fake-issue-idk)
    let insideCircle = (x <= 0 && y >= 0) && (x * x + y * y <= R * R);
    let insideRectangle = (x >= 0 && x <= R) && (y <= R && y >= R / 2);
    let insideTriangle = (x <= 0 && y <= 0) && (y >= -x - R);
    return insideCircle || insideRectangle || insideTriangle;
}

function addPointCanvas(x, y, r, isHit) {
    var canvas = document.getElementById('graph');
    var context = canvas.getContext('2d');

    console.log(x, y, r, isHit)

    x = x * (200 / r) + 300 - 5;
    y = 300 - y * (200 / r) - 5;

    if (isHit)
        context.fillStyle = '#f00';
    else
        context.fillStyle = '#15ff00';
    context.fillRect(x, y, 10, 10);
}
